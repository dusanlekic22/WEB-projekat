Vue.component('city',{
template:
`
  <button type="button" class="btn-lg ">
    {{ name }}<i class="arrow right"></i>
  </button>
`,
  props: ["id", "name"],
mounted() {
        let style = document.createElement('link');
      style.type = "text/css";
        style.rel = "stylesheet";
        style.href = 'css/cities.css';
        document.head.appendChild(style);
    }
});
