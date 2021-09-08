Vue.component('base-user', {
    template:
        
` 
  	<div class="d-flex pt-2">
        <div class="pt-2  m-auto">{{ username }}</div>
        <div class="pt-2  m-auto">{{ name }}</div>
        <div class="pt-2  m-auto">{{ surname }}</div>
    </div>
`,
    data() {
      return {
        };
  },
  props: [
     'username', 'name' ,'surname'
  ],

});