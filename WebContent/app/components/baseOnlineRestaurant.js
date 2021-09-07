Vue.component('base-online-restaurant', {
template:
`
<div class="col-md-4 mb-3">
      <div class="card h-100">
        <div class="d-flex justify-content-between position-absolute w-100">
        </div>
        <div class="img-hover-zoom img-hover-zoom--xyz">
          <img src="https://picsum.photos/700/550" class="card-img-top" alt="Product">
          <div class="cardOverlay" @click="open">Pogledaj <br>
         {{ name }} <br> ponudu</div>
        </div>
        <div class="card-body px-2 pb-2 pt-1">
          <div class="d-flex justify-content-between">
            <div>
              <p class="h4"> {{ name }}</p>
            </div>
            <div>
              <a href="#" class="text-secondary lead" data-toggle="tooltip" data-placement="left" title="Compare">
                 <p class="starcolor  d-flex align-items-center mb-2">
            <i class="fa fa-star" aria-hidden="true"></i>
            <i class="fa fa-star" aria-hidden="true"></i>
            <i class="fa fa-star" aria-hidden="true"></i>
            <i class="fa fa-star" aria-hidden="true"></i>
            <i class="fa fa-star-half-o" aria-hidden="true"></i>
          </p>
              </a>
            </div>
          </div>
          <p class="mb-0">
            <strong>
              <a href="#" v-if="isOpen === 'OPEN'" class="text-secondary">Otvoren</a>
              <a href="#" v-else class="text-secondary">Zatvoren</a>
            </strong>
          </p>
          <p class="mb-1">
            <small>
              <a href="#" class="text-secondary">{{type}}</a>
            </small>
          </p>
          </div>
        </div>
      </div>
    </div>

`,

    mounted() {
        $('#baseDialogForm').modal('show');
        let style = document.createElement('link');
        style.type = "text/css";
        style.rel = "stylesheet";
        style.href = 'css/baseOnlineRestaurant.css';
        document.head.appendChild(style);
        console.log(this.isOpen);
    },
    methods: {
        open() {
            console.log("kliknuo sam");
        }
    },
    props: ['name','isOpen','type']
});
