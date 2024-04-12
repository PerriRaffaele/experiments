<template>
  <div class="container">
    <h1>THANKS FOR YOUR TIME </h1>
    <button @click="startNewExperiment">Start New Experiment</button>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router';

const router = useRouter();

const startNewExperiment = () => {
  // Assuming you have a route named 'welcome'
  router.push({ name: 'welcome' });
};
</script>

<style scoped>
/* Your scoped styles here */
</style>
