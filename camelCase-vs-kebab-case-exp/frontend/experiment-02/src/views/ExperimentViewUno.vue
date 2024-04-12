<template>
  <div>
    <h1>Experiment {{ this.currentExperiment }}</h1>

    <div v-if="this.counter < 5">
      <!-- Box above the clickable boxes -->
      <div class="box">{{ boxWords.options_kebab_case.original_word }}</div>

      <!-- Four clickable boxes side by side -->
      <div class="boxes">
        <div v-for="(word, index) in currentWords" :key="index" @click="boxClicked(word)" class="clickable-box">
          {{ word }}
        </div>
      </div>
      <div class="clear-float"></div>

    </div>
    <div v-else>
      <div class="box">{{ boxWords.options_camel_case.original_word }}</div>

      <!-- Four clickable boxes side by side -->
      <div class="boxes">
        <div v-for="(word, index) in currentWords" :key="index" @click="boxClicked(word)" class="clickable-box">
          {{ word }}
        </div>
      </div>
      <div class="clear-float"></div>

    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      totalExperiments: 10,
      currentExperiment: 1,
      boxWords: {
        options_camel_case: { original_word: '', options_camel_case: [] },
        options_kebab_case: { original_word: '', options_kebab_case: [] }
      },
      currentCase: 'kebabCase', // Initial case
      counter: 0,
      startTime: null,
      attempt:0,
    };
  },
  methods: {
    async fetchBoxWords() {
      try {
        this.startTime = performance.now();
        const response = await axios.get('http://localhost:5000/words');
        this.boxWords = response.data;
        console.log('Fetched words:', this.boxWords);

        if (this.counter === this.totalExperiments) {
          // Redirect to a new page after 10 experiments
          this.$router.push('/end-view');
        }
      } catch (error) {
        console.error('Error fetching words:', error);
      }
    },
    boxClicked(word) {
      // Handle box click event
      const endTime = performance.now();
      const timeTaken = endTime - this.startTime;
      let isCorrect = "";
      this.currentCase = this.counter < 5 ? 'kebabCase' :'camelCase';
      if (this.currentCase === "camelCase") {
        isCorrect = word === this.boxWords.options_camel_case.correct_camel_case;
      } else {
        isCorrect = word === this.boxWords.options_kebab_case.correct_kebab_case;
      }

      const boxElement = event.target;

      if (isCorrect) {
        // Add the correct-box class to the clicked box
        boxElement.classList.add('correct-box');
      } else {
        // Add the incorrect-box class to the clicked box
        boxElement.classList.add('incorrect-box');
      }

      // Remove the correct-box or incorrect-box class after a brief delay (e.g., 1000 milliseconds)
      setTimeout(() => {
        boxElement.classList.remove('correct-box', 'incorrect-box');
      }, 300);


      if(isCorrect) {
        let bool = true;
        if (this.attempt !== 0){
          bool = false;
        }
        let answerData = {
          ex:1,
          age: this.participantData.age,
          gender: this.participantData.gender,
          programming_experience: this.participantData.programmingExperience,
          camel_case_familiarity: this.participantData.familiarityCamelCase,
          kebab_case_familiarity: this.participantData.familiarityKebabCase,
          type: this.currentCase === "camelCase" ? "camelCase" : "kebabCase",
          word: this.currentCase === "camelCase" ? this.boxWords.options_camel_case.original_word : this.boxWords.options_kebab_case.original_word,
          clickedWord: word,
          bool,
          timeTaken
        };



        this.submitAnswerData(answerData);
        this.counter = this.counter + 1;
        this.currentExperiment = this.currentExperiment + 1;
        this.fetchBoxWords();
        console.log("currentCase", this.currentCase);
        this.attempt = 0;
      } else {
        isCorrect = "";
        this.attempt = this.attempt + 1;
      }
    },
    async submitAnswerData(answerData) {
      try {
        // Make a POST request to the combined endpoint
        const response = await axios.post('http://localhost:5000/submit-and-export', answerData);
        console.log('Submitted answer data:', response.data);

        // Check if it's time to export to CSV (e.g., after 10 experiments)
        if (this.counter === this.totalExperiments) {
          // Make a GET request to trigger CSV export
          const exportResponse = await axios.get('http://localhost:5000/submit-and-export');
          console.log('Exported answer data to CSV:', exportResponse.data);

          // Optionally, you can redirect to a new page after exporting to CSV
          this.$router.push('/end-view');
        }
      } catch (error) {
        console.error('Error submitting answer data:', error);
      }
    },
  },
  computed: {
    currentWords() {
      // Return words based on the current case and counter value
      return this.counter < 5
          ? this.currentCase === 'kebabCase' ? this.boxWords.options_camel_case.options_kebab_case : []
          : this.boxWords.options_kebab_case.options_camel_case;
    },
  },
  mounted() {
    this.fetchBoxWords();
  },
  created() {
    const participantData = this.$route.params.participantData;
    if (participantData) {
      this.participantData = JSON.parse(participantData);
    }
  }
};
</script>

<style scoped>
/* Add component styles here */
h1 {
  text-align: center;
  margin-top: 20%;
}

.box {
  margin-top: 20px;
  background-color: transparent;
  border: 1px solid #e0e0e0;
  padding: 10px;
  font-size: 20px;
  width: calc(90%); /* Adjust the width to account for margin and border */
}

.clickable-box {
  margin-top: 10px;
  margin-right: 10px;
  background-color: transparent;
  border: 1px solid #e0e0e0;
  padding: 10px;
  cursor: pointer;
  display: flex;
  width: calc(48% - 20px); /* Adjust the width to account for margin and border */
  height: 100px;
  box-sizing: border-box;
  transition: transform 0.3s; /* Smooth transition for the transform property */
  text-align: center;
  font-size: 20px;
  align-items: center;
  justify-content: center;
}

.boxes {
  display: flex;
  flex-wrap: wrap;
}
.correct-box {
  background-color: green; /* Set the background color for the correct answer */
}

.incorrect-box {
  background-color: red; /* Set the background color for the incorrect answer */
}

.clickable-box:hover {
  transform: scale(1.1); /* Increase the size on hover */
}
</style>
