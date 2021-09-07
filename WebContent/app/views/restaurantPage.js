Vue.component('restaurant-page', {
  template:
  `
 <div class="container-fluid">
	<div id="restaurantPageSection">
		<div class="container py-5">
			<div class="row py-4">
				<div class="col-lg-7 pt-5 text-center restaurant">
					<h1 class="pt-5">
              Želiš da isprobaš nešto novo?
          </h1> </div>
			</div>
		</div>
		<div class="gradeAndSearch">
			<div class="container pt-4">
				<div class="d-flex "> <img src="img/1.gif">
					<div id="grade">
						<h3>9.6<sup>od 10</sup></h3> </div>
					<div class="ml-auto">
						<div id="articleSearch">
							<input type="search" /> </div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="restaurantPageArticle">
		<div class="container pt 2">
			<div class="row ">
				<div class="col-md-2"> </div>
				<div class="col-md-8"> 
		     <base-article :ida="1" @dodaj="noviArtikal"></base-article>
          <base-article :ida="2" @dodaj="noviArtikal"></base-article>
           <base-article :ida="3" @dodaj="noviArtikal"></base-article>
        </div>
				<div class="col-md-2">  </div>
			</div>
		</div>
	</div>
</div>
  `,
  name: "Home",
  data() {
      return {
      username: '',
      password: '',
      confirmPassword: '',
      firstname: '',
      surname: '',
      gender: '',
      dateofbirth: '',
      loginUsername: '',
      loginPassword: '',
      role: '',
      korpa: []
    };
  },
   mounted() {
        let style = document.createElement('link');
        style.type = "text/css";
        style.rel = "stylesheet";
        style.href = 'css/restaurantPage.css';
       document.head.appendChild(style);
  },
  computed: {
  },
  methods: {
    noviArtikal(value) {
      if (this.korpa.length === 0) {
          this.korpa.push(value);
      }
      else {
        var notInlist = true;
        this.korpa.forEach(element => {
          if (element.id === value.id) {
            this.korpa[this.korpa.indexOf(element)] = value;
            notInlist = false;
          }
        });
        if (notInlist) {
         this.korpa.push(value);
      }    
      
      }
      // this.korpa.push(value);
      console.log(this.korpa);
    }
    },

});
