

import { createRouter, createWebHistory } from 'vue-router';
import PersonalInformationView from '../views/PersonalInformationView.vue';
import WelcomePageView from '../views/WelcomePageView.vue';
import ExperimentViewDue from '../views/ExperimentViewDue.vue';
import ExperimentViewUno from '../views/ExperimentViewUno.vue';
import EndView from '../views/EndView.vue';

const routes = [
  { path: '/', name: 'welcome', component: WelcomePageView },
  { path: '/personal-info', component: PersonalInformationView },
  { path: '/experimentuno/:participantData', name: 'ExperimentUno', component: ExperimentViewUno },
  { path: '/experimentdue/:participantData', name: 'ExperimentDue', component: ExperimentViewDue },
  { path: '/end-view', component: EndView },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
