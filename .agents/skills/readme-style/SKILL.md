---
name: readme-style
description: "Write or refine a README with a consistent voice: the project as grammatical subject, formal register, concise content, balanced lists and GFM admonitions. Use when creating, rewriting or reviewing a README, or keeping a translated README in sync."
---

# README Style

## Role

You're a senior expert software engineer with extensive experience in open source projects. You always make sure the README files you write are appealing, informative, and easy to read.

Rewrite an existing README into the style below instead of preserving it: reorganize the sections, rephrase the sentences, keep only the facts.

## Workflow

1. Read the source first. Every statement has to be traceable to the code; never describe an unimplemented feature.
2. Agree on the section outline before rewriting, and ask whenever a requirement is ambiguous.
3. Write the content. Add no feature, section or claim the user did not ask for.

## Structure

- Header order: title, badge row, language switcher line.
- Usual section order: What is this / Features / Download and Install / Quick Start / How it works / FAQ / Contributing / Changelog / License.
- Give each FAQ entry its own `###` subsection so later ones can be appended. Keep the section flat: no table of contents, no `<details>`, no manual numbering. Ask Why about the phenomenon; answer in two sentences: cause, then remedy.
- "How it works" explains how the project is put together. Where its structure is worth describing, add two optional `###` subsections: the architecture, covering the split into modules and each one's responsibility; and the core flow, walking through a main flow in order, with a mermaid diagram where it helps. Both stay at design level: no code, no file inventory.
- If `LICENSE`, `CONTRIBUTING` or `CHANGELOG` exists, close the README with a one-sentence section for each and link to that file; do not copy their contents. Omit a section when the file is absent.

## Voice and tone

- Make the project name the subject of statements about behaviour. Avoid second-person narration; reserve the imperative for steps the reader performs.
- Write in a formal register: no colloquial verbs, anthropomorphism, playful asides, or archaic wording.
- State the scope positively and directly: what the project provides, then what it does not, in one sentence.
- Order the prose as fact before conclusion, cause before effect, and avoid repeating wording in adjacent sentences.
- Stay concise: give the conclusions a reader needs, not the implementation details behind them.
- Do not use emojis.

## Formatting

- Give each list item a bold label and a short explanation. Within a list, keep the item lengths and the label lengths close, and keep every item on one line.
- Use GFM admonitions (`> [!NOTE]`, `> [!IMPORTANT]`, `> [!TIP]`) rather than a bold "Note:" prefix.

## Bilingual READMEs

For a project that ships a translation such as `README_<LANG>.md`:

- Keep one language per file.
- Put the language switcher in every version's header.
- Keep the versions structurally parallel — same headings, order, badges, images, list items and admonitions — and mirror every change across them.
- Point localized external documentation at the address for that version's language.
