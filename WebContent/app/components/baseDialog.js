
Vue.component('base-dialog', {
template:
`
<div id= "baseDialogOverlay">
  <div class="modal fade" id="baseDialogForm" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" @click="check"  @hide="check" >
  <div class="modal-dialog modal-dialog-centered" role="document">
   <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header border-bottom-0">
         <slot name="header"></slot>
      </div>
       <slot name="body"></slot>
    </div>
      <div class="modal-footer">
            <slot name="footer"></slot>
     </div>
    </div>
  </div>
</div>
</div>

  
  
`,
    data() {
        return {
          firstname: '',
      lastname: '',
      email: '',
      password: '',
      storedData: '',  
        };
    },
    mounted() {
        $('#baseDialogForm').modal('show');
        let style = document.createElement('link');
        style.type = "text/css";
        style.rel = "stylesheet";
        style.href = 'css/baseDialog.css';
        document.head.appendChild(style);
    },    
    methods: {
        closeRegistration() {
            this.$store.commit("closeRegistration");
        },
        check(evt) {
            console.log(evt.srcElement.id);
            if (evt.srcElement.id === "baseDialogForm") {    
                this.closeRegistration();
      }
            }
    },
});