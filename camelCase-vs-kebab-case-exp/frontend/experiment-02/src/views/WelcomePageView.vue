<!-- App.vue -->
<template>
<h1 class="welcome-heading">Welcome to the Identifier Reading Experiment</h1>
  <div class="welcome-container">
    
    <p>
      In natural language reading studies, people read better when an explicit separator is used between words. Whether
      it's a white space or a special symbol, the overall message is that reading "this text" is more efficient than
      reading "this text" or "this_text."
    </p>

    <div class="section">
      <h2>Research Question:</h2>
      <p>
        Is this finding valid for source code as well? In other words, can we speed up code reading using a specific
        separator when writing composed identifiers (i.e., identifiers featuring more than one word)?
      </p>
    </div>

    <div class="section">
      <h2>Experimental Design:</h2>
      <p>
        We aim to investigate whether people read faster with identifiers written in <strong>camelCase</strong> or
        <strong>kebab-case</strong>.
      </p>
    </div>


    <div class="section">
      <h2>Instructions:</h2>
      <p>
        You will be presented with code snippets containing identifiers in either camelCase or kebab-case. Your task is to
        read these snippets, and we will measure the time it takes for you to do so. Please focus on both speed and
        comprehension.
      </p>
    </div>

    <div class="section">
      <h2>Participant Information:</h2>
      <p>
        Before we begin, we would like to gather some information about you. Please answer the following questions.
      </p>
    </div>
    <router-link to="/personal-info">
      <button @click="startExperiment">Start Experiment</button>
    </router-link>
  </div>
</template>

<script>
import Button from '../components/Button.vue';

export default {
  components: {
    Button
  },
  data() {
    return {
      buttons: [],
      clickStartTime: null,
      clickTime: null
    };
  },
  mounted() {
  },
  methods: {
    startExperiment() {
      this.$router.push('/personal-info');
    },
  }
};
</script>

<style scoped>
.welcome-heading {
  text-align: center;
}
.welcome-container {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  text-align: justify;
}

.section {
  margin-bottom: 20px;
}

button {
  background-color: #4caf50;
  color: white;
  padding: 10px 20px;
  font-size: 16px;
  cursor: pointer;
  border: none;
  border-radius: 5px;
  display: block; /* Make the button a block-level element */
  margin: 20px auto; /* Center the button horizontally by setting margin-left and margin-right to 'auto' */
}

button:hover {
  background-color: #45a049;
}
</style>