import re
import random
import uuid

def generate_task(sentence):
    correct_camel_case = to_camel_case(sentence)
    correct_kebab_case = to_kebab_case(sentence)

    # Generate distractors
    distractors_camel_case = generate_distractors(correct_camel_case)
    distractors_kebab_case = generate_distractors(correct_kebab_case)

    # Shuffle options to avoid bias
    options_camel_case = random.sample([correct_camel_case] + distractors_camel_case, k=4)
    options_kebab_case = random.sample([correct_kebab_case] + distractors_kebab_case, k=4)

    # Create the result object
    result_object = {
        'original_word': sentence,
        'options_camel_case': options_camel_case,
        'options_kebab_case': options_kebab_case,
        'correct_camel_case': correct_camel_case,
        'correct_kebab_case': correct_kebab_case
    }

    return result_object

def to_camel_case(sentence):
    # Remove punctuation
    sentence = re.sub(r'[^\w\s]', '', sentence)

    # Split sentence into words
    words = sentence.split()

    # let the first word be lowercase
    words[0] = words[0].lower()

    # Capitalize the other words
    for i in range(1, len(words)):
        words[i] = words[i].capitalize()

    # Join words together
    return ''.join(words)

def to_kebab_case(sentence):
    # Remove punctuation
    sentence = re.sub(r'[^\w\s]', '', sentence)

    # Split sentence into words
    words = sentence.split()

    # Convert words to lowercase
    words = [word.lower() for word in words]

    # Join words together with hyphens
    return '-'.join(words)

def generate_distractors(correct_answer):
    # Generate distractors
    distractors = []
    for i in range(3):
        # Generate random index
        random_index = random.randint(0, len(correct_answer) - 1)

        # Generate random character
        random_char = chr(random.randint(97, 122))

        # Insert random character at random index
        distractor = correct_answer[:random_index] + random_char + correct_answer[random_index:]

        # Add distractor to list
        distractors.append(distractor)

    return distractors

def generate_random_user_id():
    return str(uuid.uuid4())
    



    
