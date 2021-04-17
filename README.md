COMS W6156 Final Project (imposters2)
========================================

### Team Members
Sang Jun Chun (sc4658) and Claire (Yumeng) Luo (yl4655)

Project Overview
----------------------------
Code similarity detection is used in plagiarism check, defect detection, etc. In our project, we utilized code similarity detection tools to detect plagiarism in school projects. 

In this repo, you will find accompanying data and tools that we used, as well as directions for replication.


Repository Structure
----------------------------
```/all_submissions```: contains proposal, revised proposal, first progress report, second progress report, demo slides, and final report

```/data```: contains various data used for this project
- ```/data/encoding_data```: encoding data for DeepSim
- ```/data/original_github_data```: original submissions for 4156 IP1 downloaded from public GitHub repo
- ```/data/SIM_data```: modified code for SIM to process
- ```/data/simulated_plagiarism```: artificially created plagiarism cases
- ```/data/skeleton_code```: initial skeleton code made available for 4156 IP1 project

```/tools```: contains code & configuration for tools used

```/utility```: miscellaneous scripts and input for data processing

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

### SIM
- The most recent official version (released in 2017) contains Makefile and other compatibility issues. A fixed version published at https://github.com/andre-wojtowicz/sim is used instead.
- The fixed version of SIM is available in /tools/sim-master
- To install SIM, follow the steps outlined in the README file at: https://github.com/andre-wojtowicz/sim/blob/master/project/README
- Data is modified so that SIM can easily process them. The modified data can be found in /data/SIM_data
- To report similarity percentages among pairs, run the following command: ```/tools/sim-master/project/sim_c -e -p -s -o ~/text/output.txt ~/text/*.txt```
- Output for all ```.txt``` files in ```~/text/``` will be saved at ```~/text/output.txt```.
- Documentation can be found at: https://dickgrune.com/Programs/similarity_tester/sim.pdf.
