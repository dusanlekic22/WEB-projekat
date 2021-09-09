Vue.component('base-order', {
template:
`
<div class="col-md-3 comment"> 
 <div class="row">
    <div class="col-6 col-sm-6">
      <div class="comment-header">
        {{ id }}
      </div>
    </div>
      <div class="col-3 col-sm-3">
      <div class="comment-header">
        {{ restId }}
      </div>
    </div>
    <div class="col-6 col-sm-6">
      <div class="comment-header">
        {{ price }}
      </div>
    </div>
    <div class="col-6 col-sm-6">
      <div class="comment-header">
        {{ status }}
      </div>
    </div>
      <div class="col-3 col-sm-3">
        <slot></slot>
     </div>
 </div>
</div>

  
  
`,
   
    mounted() {
        let style = document.createElement('link');
        style.type = "text/css";
        style.rel = "stylesheet";
        style.href = 'css/baseComment.css';
        document.head.appendChild(style);
    },
    data() {
        return {
            tekstKomentara: 'Solidna dostava',
            ocena: null
        };
    },
    methods: {
    },
    props:['id','restId','price','status']
});