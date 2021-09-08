Vue.component('base-article', {
    template:
        
` 
  <div class="articleRow" @click="change($event)">
   <transition name="fullAImg">
  <div class="fullArticleImg" :class="fullTheme"  v-show="isClicked" >
     <img   src="https://picsum.photos/700/550" />
              
  </div>
  </transition>
<div class="d-flex">
		<div class="p-2" v-show="brojPorucenih === 0" @click="dodajUKorpu()" > <i class="fa fa-plus iplus" aria-hidden="true"></i> </div>
		<div class="p-2 pt-4" style="color: lightblue" v-show="brojPorucenih > 0" > <h3>{{brojPorucenih}}x </h3></div>
    <div class="p-2">
            <div class="col-lg plus">
           <h3> {{name}} </h3>
            </div>
            <div class="col-lg">
          {{description}}
            </div>
            <div class="col-lg" style="color:blue;">
            <h5>{{price}}</h5>
            </div>
        </div>
        <transition name="aImg">
		<div :class="theme" class="ml-auto pt-2 "    v-show="!isClicked">
        <img class="slika" src="https://picsum.photos/700/550" /> </div>
        </transition>
        </div>
        <div v-show="isClicked" class="row addButtons">
          <div class= "col-3 pt-4">
            <div class="naslov">
              <h4>Broj: {{ brojPorucenih }} </h4>
            </div>
          </div>
          <div class= "col-4">
          <div class="naslov">
        </div></div>
          <div class="col-3 pt-4">
            <div class="d-flex">
              <div class="pt-2">
                <button class="buttonCartMinus" @click="ukloniIzKorpe()"> - </button>
              </div>
              <div class="pt-2">
              <button class="buttonCartPlus"  @click="dodajUKorpu()"> + </button></div>
              </div>
            <div>
        </div>
       </div>
         </div>
</div>
`,
    data() {
      return {
            id: this.ida,
            isClicked: false,
            manji: false,
            theme: 'idA',
            startTheme: 'id',
            fullTheme: 'id',
            brojPorucenih: 0,
            ukupnaCena: 0
        };
  },
  props: [
     'ida','name','description','price','quantity'
  ],
    mounted() {
        $('#baseDialogForm').modal('show');
        let style = document.createElement('link');
        style.type = "text/css";
        style.rel = "stylesheet";
        style.href = 'css/baseArticle.css';
      document.head.appendChild(style);
      this.isClicked = false;
      this.theme = "idA" + this.id;
      this.fullTheme = "idF" + this.id
      if(this.quantity>0){
        this.brojPorucenih = this.quantity;
      }
    },    
    methods: {
        closeRegistration() {
            this.$store.commit("closeRegistration");
      },
      closeLogin() {
      this.$store.commit('loginModule/closeLogin');
     },
      check(evt) {
        if (evt.srcElement.id === "baseDialogForm") {
          $("#baseDialogForm").mousedown(function(){
}); 
          this.closeRegistration();
          this.closeLogin();
         
        }
        },
      change(evt) {
        if (evt.srcElement.className === 'buttonCartPlus' || evt.srcElement.className === 'fa fa-plus iplus' || evt.srcElement.className === 'buttonCartMinus') {
        }
        else{
          this.isClicked = !this.isClicked;
        if (this.isClicked === false) {
          console.log($('.ml-auto '));
            // $('.ml-auto')[this.id].style='opacity:0';
            $('.'+this.theme).css('opacity', '0');
            $('.'+this.fullTheme).css('margin-top', '0px');
          setTimeout(() => {
            $('.'+this.theme).css('opacity', '1');
            // $('.ml-auto')[this.id].style='opacity:1';
            $('.'+this.fullTheme).css('margin-top', '-300px');
            }, 1);


          }
          else {
          //  $('.ml-auto')[this.id].style='opacity:1';
            $('.'+this.theme).css('opacity', '10');
            $('.'+this.fullTheme).css('margin-top', '-300px');
            setTimeout(() => {
            // $('.ml-auto')[this.id].style='opacity:0';
            $('.'+this.theme).css('opacity', '0');
            $('.'+this.fullTheme).css('margin-top', '0px');
            }, 1);
          }}
      },
      dodajUKorpu() {
        this.brojPorucenih = this.brojPorucenih + 1;
        this.$emit('dodaj', {
          "id": this.id,
          "brojPorucenih": this.brojPorucenih
        });
       },
      ukloniIzKorpe() {
        if (this.brojPorucenih - 1 <= 0) {
          this.brojPorucenih = 0;
        }
        else {
             this.brojPorucenih = this.brojPorucenih - 1;
        }
      },
      
    },
});