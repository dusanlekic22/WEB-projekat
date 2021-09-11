Vue.component('base-comment', {
template:
`
<div class="col-12 comment"> 
 <div class="row">
    <div class="col-6 col-sm-6">
      <div class="comment-header">
       Komentar:  {{ komentar }}  Korisnika: {{ username }}
      </div
    </div>
      <div class="col-6 col-sm-3"></div>
      <div class="comment-header">
      za restoran {{restaurantId}} je nova ocena: {{ ocena }}
      </div
    </div>
      <div class="col-6 col-sm-3">
      <div class="row">
        <div class="col-3 col-sm-3">
        <button @click.prevent="approve">Odobri</button>
        </div>
         <div class="col-3 col-sm-3">
         <button @click.prevent="decline">Ponisti</button>
        </div>
      </div>
     </div>
 </div>
</div>
</div>
  
`,
   props:['id', 'komentar', 'ocena', 'username', 'restaurantId'],
    mounted() {
        let style = document.createElement('link');
        style.type = "text/css";
        style.rel = "stylesheet";
        style.href = 'css/baseComment.css';
        document.head.appendChild(style);
    },
    data() {
        return {
        };
    },
    methods: {
    approve(){
      this.$emit('approve', {
        "status": "APPROVED",
        "id": this.id
      });
    },
    decline(){
        this.$emit('decline', {
        "status": "DENIED",
        "id": this.id
      });
    }
    },
});