import fs from 'fs';
import { OpenAI } from 'openai';

const openai = new OpenAI({ apiKey: process.env.OPENAI_KEY });

async function updateSummaryMD() {
    // Load current summary and git diff
    const summaryPath = './docs/updates.md';
    const diffPath = './git-diff/docs-2.txt'; // You'd generate this from git diff

    const summary = fs.readFileSync(summaryPath, 'utf8');
    const diff = fs.readFileSync(diffPath, 'utf8');

    const systemPrompt = 
        `You are a technical writer assistant maintaining documentation for a codebase. 
You update documentation to reflect the latest code changes clearly and accurately.
Write in clear, concise markdown format suitable for developers.
Use headings, bullet points, and code blocks as needed.
Avoid adding any disclaimers or apologies.
Document only public APIs and features, not internal implementation details.`;
    

    const userPrompt = `
Here is the current summary file:
\`\`\`markdown
${summary}
\`\`\`

Here are the changes from the latest PR (git diff):
\`\`\`diff
${diff}
\`\`\`

Update the summary file accordingly. Keep existing content intact unless it is now outdated. Insert new sections or revise text where relevant.
`;

    const response = await openai.chat.completions.create({
        model: 'gpt-4o', // or 'gpt-4o-mini' for lower-cost runs
        messages: [
            { role: 'system', content: systemPrompt },
            { role: 'user', content: userPrompt },
        ],
        temperature: 0.3,
    });

    const updatedSummary = response.choices[0].message.content;

    // Overwrite the file or commit in Git
    fs.writeFileSync('./docs/updates.md', updatedSummary);
    console.log('✅ summary.md updated.');
}

updateSummaryMD().catch(console.error);
