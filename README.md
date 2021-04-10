# 6156-imposters2

SIM text diff results:
Ground truth: comparison between non-plagiarized submissions (14 and 36)

Sample plagiarism (/simulated\ plagiarism)
- Manually created plagiarized code based on submission 14 (junchun). Confirmed that all samples compile successfully.
- lines_removed: attempts to fool the code similarity detector by adding meaningless or redundant code snippets. Also removed comments (e.g. Javadoc) or removed lines when possible.
- structure_changed: changes the order of methods defined
- variables_changed: changes the variable types and names.
- all_changed: all changes listed above are applied here.