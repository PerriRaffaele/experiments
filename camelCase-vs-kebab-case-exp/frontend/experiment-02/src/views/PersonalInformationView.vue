<!-- PersonalInformationView.vue -->

<template>
  <div v-if="!this.statingExperiment" class="personal-info-container">
    <h1>Personal Information</h1>
    <form @submit.prevent="submitForm">
      <!-- Demographics Questions -->
      <div class="form-group">
        <label for="age">Age:</label>
        <input type="number" id="age" v-model="participantData.age" required>
      </div>

      <div class="form-group">
        <label for="gender">Gender:</label>
        <select id="gender" v-model="participantData.gender" required>
          <option value="male">Male</option>
          <option value="female">Female</option>
          <option value="other">Other</option>
        </select>
      </div>
      <div class="form-group">
        <label for="gender">Programming Experience (years):</label>
        <select id="gender" v-model="participantData.programmingExperience" required>
          <option value="0 I do not now How to program">0 I do not now How to program</option>
          <option value="Less than 3">Less than 3</option>
          <option value="More than 3">More than 3</option>
        </select>
      </div>
      <div class="form-group">
        <label for="gender">Are you familiar with "camelCase"</label>
        <select id="gender" v-model="participantData.familiarityCamelCase" required>
          <option value="yes">Yes</option>
          <option value="no">No</option>
        </select>
      </div>
      <div class="form-group">
        <label for="gender">Are you familiar with "kebab-case"</label>
        <select id="gender" v-model="participantData.familiarityKebabCase" required>
          <option value="yes">Yes</option>
          <option value="no">No</option>
        </select>
      </div>
      <button type="submit">Submit</button>
    </form>
  </div>
  <div v-else class="experiment-container">
    <h1 class="experiment-title">Experiment</h1>
    <div class="experiment-description">
        In this experiment, you will be presented with 20 code snippets, featuring a mix of 10 camelCase and 10 kebab-case examples.
      For instance, in camelCase:
      <div class="snippet">
        <span class="snippet-word">programming Language   ==> </span>
        <span class="snippet-word">programmingLanguage</span>
      </div>
       and in kebab-case:
      <div class="snippet">
        <span class="snippet-word">programming Language   ==> </span>
        <span class="snippet-word">programming-language</span>
      </div>
      Each snippet will have clickable words.
      When you select a word, it will be highlighted in green if your choice is correct, or red if incorrect. In the case of a wrong answer, you must click the correct word to proceed. The experiment starts as soon as you press the "Start" button. Good luck and have fun testing your coding style knowledge!
    </div>
    <!-- Example snippet -->


    <div class="button-container">
      <button v-if="!flagCountdown && showStartButtonUno" class="start-button" @click="startExperimentUno">Start Experiment One</button>
      <button v-else-if="!flagCountdown && showStartButtonDue" class="start-button" @click="startExperimentDue">Start Experiment Two</button>
    </div>

    <div v-if="flagCountdown" class="countdown">{{ countdown }}</div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      showStartButtonUno: false,
      showStartButtonDue: false,
      participantData: {
        age: null,
        gender: null,
        programmingExperience: null,
        familiarityCamelCase: null,
        familiarityKebabCase: null,
      },
      statingExperiment: false,
      countdown: 3,
      flagCountdown: false,
    };
  },
  methods: {
    randomizeButtons() {
      // Randomly decide which button to show
      this.showStartButtonUno = Math.random() < 0.5; // 50% chance
      this.showStartButtonDue = !this.showStartButtonUno;
    },
    submitForm() {
      console.log('Participant Data:', this.participantData);
      this.statingExperiment = true;
    },
    startExperimentUno() {
      // Show countdown
      this.flagCountdown = true;
      this.showCountdown();

      // Start experiment route after countdown
      setTimeout(() => {
        this.$router.push({
          name: 'ExperimentUno',
          params: {
            participantData: JSON.stringify(this.participantData),
          },
        });
      }, (this.countdown + 1) * 1000);
    },
    startExperimentDue() {
      // Show countdown
      this.flagCountdown = true;
      this.showCountdown();

      // Start experiment route after countdown
      setTimeout(() => {
        this.$router.push({
          name: 'ExperimentDue',
          params: {
            participantData: JSON.stringify(this.participantData),
          },
        });
      }, (this.countdown + 1) * 1000);
    },
    showCountdown() {
      let count = 3; // Set the initial countdown value

      const countdownInterval = setInterval(() => {
        this.countdown--;

        if (this.countdown < 0) {
          clearInterval(countdownInterval);
          this.countdown = 0;
        }
      }, 1000);
    },
  },
  mounted() {
    this.randomizeButtons();
  }
};
</script>

<style scoped>
.personal-info-container {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
}

.form-group {
  margin-bottom: 15px;
}

label {
  display: block;
  margin-bottom: 5px;
}

input,
select {
  width: 100%;
  padding: 8px;
  box-sizing: border-box;
}

button {
  background-color: #4caf50;
  color: white;
  padding: 10px 20px;
  font-size: 16px;
  cursor: pointer;
  border: none;
  border-radius: 5px;
}

button:hover {
  background-color: #45a049;
}
.experiment-container {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
}

.experiment-title {
  font-size: 60px;
  color: #333;
  margin-bottom: 10px;
}

.experiment-description {
  font-size: 25px;
  color: #555;
  margin-bottom: 20px;
}

.start-button {
  background-color: #4caf50;
  color: white;
  padding: 10px 20px;
  font-size: 16px;
  cursor: pointer;
  border: none;
  border-radius: 5px;
}

.start-button:hover {
  background-color: #45a049;
}
.countdown {
  font-size: 32px;
  font-weight: bold;
  color: green;
  text-align: center;
  margin-top: 20px;
}
.button-container {
  display: flex;
  justify-content: center;
  margin-top: 10px;
}

.start-button {
  margin-right: 10px;
}

.experiment-title {
    color: #333;
    font-size: 24px;
    margin-bottom: 10px;
}
.experiment-description {
    color: #666;
    font-size: 16px;
    line-height: 1.6;
}
.snippet {
    background-color: #f5f5f5;
    border: 1px solid #ddd;
    border-radius: 4px;
    padding: 15px;
    margin-top: 20px;
}
.snippet-word {
    cursor: pointer;
    display: inline-block;
    margin-right: 5px;
    padding: 2px 5px;
    border-radius: 4px;
    transition: all 0.3s ease;
}
.snippet-word:hover {
    background-color: #eaeaea;
}
.correct {
    background-color: green;
    color: white;
}
.incorrect {
    background-color: red;
    color: white;
}
.start-button {
    background-color: #4CAF50;
    color: white;
    padding: 10px 20px;
    margin-top: 20px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    font-size: 16px;
}
.start-button:hover {
    background-color: #45a049;
}
</style>
