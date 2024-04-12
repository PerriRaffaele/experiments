<!-- Button.vue -->
<template>
  <button @click="handleClick">{{ label }}</button>
</template>

<script>
export default {
  props: ['label'],
  methods: {
    handleClick() {
      this.$emit('click');
    }
  }
};
</script>

<style scoped>
/* Add your styling here */
</style>
