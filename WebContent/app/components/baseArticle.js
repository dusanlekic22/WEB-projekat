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
           <h3> Uzmi pivo 0.3l </h3>
            </div>
            <div class="col-lg">
          Take a shake 0.33l Strawberry-coconut session milkshake IPA 4% 1 kom, 620.00 RSD po komadu
            </div>
            <div class="col-lg" style="color:blue;">
            <h5>633,33din</h5>
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
          <div class= "col-4"></div>
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
            korpa: {},
            brojPorucenih: 0,
            ukupnaCena: 0
        };
  },
  props: [
     'ida'
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
    },    
    methods: {
        closeRegistration() {
            this.$store.commit("closeRegistration");
      },
      closeLogin() {
      this.$store.commit('loginModule/closeLogin');
     },
      check(evt) {
        console.log("zatvaranje")
        console.log(evt);
        if (evt.srcElement.id === "baseDialogForm") {
          $("#baseDialogForm").mousedown(function(){
        console.log("Mouse button released.");
}); 
          this.closeRegistration();
          this.closeLogin();
         
        }
        },
      change(evt) {
        console.log(evt);
        console.log(evt.srcElement.className);
        if (evt.srcElement.className === 'buttonCartPlus' || evt.srcElement.className === 'fa fa-plus iplus' || evt.srcElement.className === 'buttonCartMinus') {
        }
        else{
          this.isClicked = !this.isClicked;
          console.log(this.isClicked);
        console.log(this.id);
          console.log($('.ml-auto ')[1]);
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
        this.brojPorucenih =  this.brojPorucenih + 1;
       },
      ukloniIzKorpe() {
        console.log("ukloni");
        if (this.brojPorucenih - 1 <= 0) {
          this.brojPorucenih = 0;
        }
        else {
             this.brojPorucenih = this.brojPorucenih - 1;
        }
      },
      
    },
});