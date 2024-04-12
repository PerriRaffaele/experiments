from flask import Flask, jsonify, request
from flask_cors import CORS
import word_generator
import re
import random
import csv

app = Flask(__name__)
CORS(app)

used_words = set()  # Keep track of used words

@app.route('/words', methods=['GET'])
def get_words():
    words_examples = [
        'web App', 'mobile App', 'user Interface',
        'code Example', 'programming Language', 'software Development', 'application Framework',
        'algorithm Design', 'functionality Test', 'variable Declaration', 'class Definition',
        'machine Learning', 'data Science', 'neural Network', 'visualization Technique',
        'cloud Computing', 'backend Development', 'frontend Framework', 'software Framework',
        'authentication Method', 'authorization Process', 'security Protocol', 'encryption Algorithm',
        'open Source Project', 'agile Software Development', 'version Control System',
        'responsive Web Design', 'internet of Things', 'continuous Integration',
        'full Stack Development', 'graphical User Interface', 'artificial Intelligence',
        'data Privacy Policy', 'user Experience Design', 'responsive Mobile Design',
        'distributed Systems Design', 'object Oriented Programming', 'data Visualization Tool',
        'blockchain Technology', 'internet Security Protocol', 'networking Protocol Stack',
        'software Quality Assurance', 'virtual Reality Application', 'cross Platform Compatibility',
        'machine Learning Algorithm', 'cloud Infrastructure Management', 'software as a Service',
        'data Encryption Standard', 'real Time Operating System', 'artificial Neural Network',
        'natural Language Processing', 'computer Vision System', 'computer Science Education',
        'ocean Exploration', 'modern Art Movement', 'historical Landmark', 'culinary Delight',
        'outer Space Discovery', 'ancient Civilization', 'musical Composition', 'wildlife Conservation',
        'innovative Architecture', 'cultural Heritage', 'literary Masterpiece', 'adventurous Expedition',
        'scientific Breakthrough', 'global Environmental Issue', 'exotic Travel Destination', 'philosophical Concept',
        'innovative Technology', 'natural Wonders', 'humanitarian Effort', 'sports Achievement',
        'vibrant Cultural Festival', 'archaeological Discovery', 'astronomical Phenomenon', 'botanical Diversity',
        'timeless Fashion Trend', 'celestial Navigation', 'marine Biology Study', 'culinary Fusion',
        'sustainable Energy Solution', 'artistic Expression', 'mind-bending Puzzle', 'spectacular Natural Beauty',
        'extraterrestrial Life', 'architectural Marvel', 'educational Initiative', 'ethical Business Practices',
        'mindful Living', 'experimental Music Genre', 'social Justice Movement', 'ancient Wisdom',
        'courageous Humanitarian Effort', 'spiritual Enlightenment', 'innovative Sustainable Design',
        'culinary Adventure', 'captivating Performance Art', 'historical Fiction Novel', 'intrepid Explorer',
        'cultural Diversity Celebration', 'medical Breakthrough', 'inspiring Leadership', 'community Empowerment'
    ]

    # Filter out used words   
    available_words = [word for word in words_examples if word not in used_words]

    # If all words have been used, reset the used_words set
    if not available_words:
        used_words.clear()
        available_words = words_examples
        print("Resetting used_words")

    # Print for debugging
    print("Used Words:", used_words)
    print("Available Words:", available_words)

    test_word = random.choice(available_words)

    # Add the chosen word to the set of used words
    used_words.add(test_word)

    # Print for debugging
    print("Chosen Word:", test_word)

    result_object = {
        'options_camel_case': word_generator.generate_task(test_word),
        'options_kebab_case': word_generator.generate_task(test_word)
    }

    return jsonify(result_object)

exp_number = 1

answer_data_list = []

@app.route('/submit-and-export', methods=['POST', 'GET'])
def submit_and_export():
    global exp_number  # Use the global exp_number

    if request.method == 'POST':

        # Handle POST request to submit answer data
        answer_data = request.get_json()

        # Append answer data to the in-memory list
        answer_data_list.append(answer_data)

        return jsonify({"message": "Answer data submitted successfully"})

    elif request.method == 'GET':
        # Handle GET request to export answer data to a CSV file

        # Define the CSV file path
        csv_file_path = 'answer_data.csv'

        # Define CSV header
        csv_header = [
            'User_ID','Experiment', 'Age', 'Gender', 'Programming Experience',
            'camelCase Familiarity', 'kebab-case Familiarity', "Type",
            'Word', 'Clicked Word', 'IsCorrect', 'TimeTaken'
        ]

        # Write answer data to CSV file
        with open(csv_file_path, 'a', newline='') as csv_file:
            writer = csv.DictWriter(csv_file, fieldnames=csv_header)

            # If the file is empty, write the header
            if csv_file.tell() == 0:
                writer.writeheader()

            user_id = word_generator.generate_random_user_id()
            # Write experiment information and answer data to CSV file
            for idx, answer_data in enumerate(answer_data_list, start=1):
                
                writer.writerow({
                    'User_ID': user_id,
                    'Experiment': answer_data.get('ex', ''),
                    'Age': answer_data.get('age', ''),
                    'Gender': answer_data.get('gender', ''),
                    'Programming Experience': answer_data.get('programming_experience', ''),
                    'camelCase Familiarity': answer_data.get('camel_case_familiarity', ''),
                    'kebab-case Familiarity': answer_data.get('kebab_case_familiarity', ''),
                    'Type' : answer_data.get('type', ''),
                    'Word': answer_data.get('word', ''),
                    'Clicked Word': answer_data.get('clickedWord', ''),
                    'IsCorrect': answer_data.get('bool', ''),
                    'TimeTaken': answer_data.get('timeTaken', ''),
                })
            exp_number += 1  # Increment exp_number 
   
    # Clear the answer_data_list after exporting to CSV
    answer_data_list.clear()
   
    return jsonify({"message": f"Answer data exported to {csv_file_path} successfully"})


if __name__ == '__main__':
    app.run(debug=True)