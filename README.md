COMS W6156 Final Project (imposters2)
========================================

### Team Members
Sang Jun Chun (sc4658) and Claire (Yumeng) Luo (yl4655)

Project Overview
----------------------------


Repository Structure
----------------------------
/all_submissions: contains proposal, revised proposal, first progress report, second progress report, demo slides, and final report

Data used for this project:

Code & configuration:
SIM text diff results:
Ground truth: comparison between non-plagiarized submissions (14 and 36)

Sample plagiarism (/simulated\ plagiarism)
- Manually created plagiarized code based on submission 14 (junchun). Confirmed that all samples compile successfully.
- lines_removed: attempts to fool the code similarity detector by adding meaningless or redundant code snippets. Also removed comments (e.g. Javadoc) or removed lines when possible.
- structure_changed: changes the order of methods defined
- variables_changed: changes the variable types and names.
- all_changed: all changes listed above are applied here.

Running the tools
-----------------------------------

### Plaggie
- Download contents in ```/tools/plaggie``` directory
- Install as instructed by ```README_PLAGGIE```
- Do not change anything in ```plaggie.properties```.
- Run the following command: ```java plag.parser.plaggie.Plaggie plaggie_data/```
- Results are generated in ```/tools/plaggie/result``` folder


### GEMINI
- You need to download training data (very large) from their original GitHub repository (https://github.com/xiaojunxu/dnn-binary-code-similarity)
- All the necessary files can be found in /tools/gemini
- Install as instructed by requirement.txt
- To train: ```python train.py```
- To test: Process data with ```Gemini Extract Features.ipynb``` and ```python eval.py```

### DeepSim
- Download both /tools/deepsim directory and /generate_encoding directory
- Import generate encoding project and generate 128*128*88 encoding by specifying ```.jar``` file path
- Process the encoding files (```.txt``` format) with DeepSim Embedding.ipynb
- To train and test, run: ```python classification.py```

#### SIM
- The most recent official version (released in 2017) contains Makefile and other compatibility issues. A fixed version published at https://github.com/andre-wojtowicz/sim is used instead.
- The fixed version of SIM is available in /tools/sim-master
- To install SIM, follow the steps outlined in the README file at: https://github.com/andre-wojtowicz/sim/blob/master/project/README
- Data is modified so that SIM can easily process them. The modified data can be found in /data/SIM_data
- To report similarity percentages among pairs, run the following command: ```/tools/sim-master/project/sim_c -e -p -s -o ~/text/output.txt ~/text/*.txt```
- Output for all ```.txt``` files in ```~/text/``` will be saved at ```~/text/output.txt```.
- Documentation can be found at: https://dickgrune.com/Programs/similarity_tester/sim.pdf.
