from collections import Counter, namedtuple
from typing import List

vowels = 'AEIOU'
CountingResults = namedtuple('CountingResults', 'max minor total')


def max_minor_total_count(counter: Counter) -> CountingResults:
    total_count = 0
    max_count = 0
    for symbol, count in counter.items():
        if count > max_count:
            max_count = count
        total_count += count

    return CountingResults(max_count, total_count - max_count, total_count)


def solve_first():
    with open('inputs/consistency_chapter_1_input.txt', 'r') as f:
        input_lines = f.readlines()

    first_line = True
    answers: List[int] = []
    for line in input_lines:
        if first_line:
            first_line = False
            continue
        line = line.strip()
        vowel_counter = Counter(''.join(letter for letter in line if letter in vowels))
        consonant_counter = Counter(''.join(letter for letter in line if letter not in vowels))
        different_vowels = sum(vowel_counter.values())
        different_consonants = sum(consonant_counter.values())
        if different_vowels + different_consonants < 2:
            answers.append(0)
            continue
        if different_vowels == 0 or different_consonants == 0:
            answers.append(len(line))
            continue
        vowel_counting_results = max_minor_total_count(vowel_counter)
        consonant_counting_results = max_minor_total_count(consonant_counter)
        to_vowel_seconds = vowel_counting_results.minor * 2 + consonant_counting_results.total
        to_consonant_seconds = consonant_counting_results.minor * 2 + vowel_counting_results.total
        answers.append(min(to_vowel_seconds, to_consonant_seconds))

    with open('outputs/consistency_chapter_1_output.txt', 'w') as f:
        output = ''
        for i, each in enumerate(answers):
            output += f'Case #{i + 1}: {each}\n'
        f.write(output)
