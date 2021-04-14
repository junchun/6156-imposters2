import sys
import pandas as pd
import matplotlib.pyplot as plt

def filter_tuple(tuple_list, t1, t2):
    for x, y in tuple_list:
        if (x == t1 and y == t2) or (x == t2 and y == t1):
            return True

if len(sys.argv) < 2:
    print("Invalid argument. Usage: python3 ./table_generator.py file_directory")
    exit()

filename = sys.argv[1]
plagiarism_pairs = []
# known_cheat_pairs = [(2,4), (5,19), (5,15), (15,31), (7,21), (19,33), (19,29), (6,15), (5,16)]

# simulated plagiarism text SIM results
cheat_results = [92, 79, 85, 46, 41, 41, 76, 49, 45, 44]
count = len(cheat_results)
total_weight = sum(cheat_results)

f = open(filename, "r")

# Find the averaged similarity % among plagiarized submissions
# for line in f.readlines():
#     subparts = line.split()
#     primary = subparts[0].split('.')[0]
#     secondary = subparts[2].split('.')[0]
#     percentage = int(subparts[1].split('%')[0])
#     if filter_tuple(known_cheat_pairs, int(primary), int(secondary)):
#         print("Found a cheating pair: " + primary + ", " + secondary)
#         total_weight += percentage
#         count += 1

average = total_weight/float(count)
# print("Total weight:", total_weight)
# print("Cheating pairs found:", count)
print("Cheat code average similarity score:", average)

f.seek(0)
for line in f.readlines():
    subparts = line.split()
    primary = subparts[0].split('.')[0]
    secondary = subparts[2].split('.')[0]
    percentage = int(subparts[1].split('%')[0])

    # 47% sim between junchun and yumeng's submissions
    # threshold = 11 + 47
    threshold = average

    if percentage > threshold:
        print("Plagiarism suspect found (above", threshold, "percent) --> percentage: ", percentage, ", id1: ", primary, ", id2: ", secondary)
        percentage_notataion = str(percentage) + '%'
        plagiarism_pairs.append((primary, secondary, percentage_notataion))

print("\n\nList of suspicious pairs found (id1, id2, %):")
print(plagiarism_pairs)

