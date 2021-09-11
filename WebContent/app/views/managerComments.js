Vue.component('manager-comments', {
    template:
       `<div class="container-fluid">
        <div id="restaurantPageSection">
        </div>
     <div  id="restaurantPageArticle">
        <div class="container pt 2">
           <div class="row ">
              <div class="col-md-2"> </div>
              <div  class="col-md-8" v-if=" commentsNot.length !== 0 ">
               <base-comment v-for="c in comments" :komentar="c.text" :ocena="c.rating" :username="c.user.username" :id="c.id" :restaurantId="c.restaurantId"
               @approve="updateComment" @decline="updateComment" :noButton="false"></base-comment>
           </div>
        </div>
     </div>
  </div>
  </div>
  `,
    name: 'NotApprovedComments',
    data() {
       return {
          comments: []
       };
    },
    mounted() {
       let style = document.createElement('link');
       style.type = "text/css";
       style.rel = "stylesheet";
       style.href = 'css/restaurantPage.css';
       document.head.appendChild(style);
       axios.get('rest/comments/notApproved').then( response =>{
       this.comments = response.data;
       }).catch();
    },
    methods: {
      
        updateComment(value){
        console.log("UPDATE");
        console.log(value);
     axios.put('rest/comments/updateComment/'+ value.id,
                   
                      value.status
                   ).then(response => {
                    this.comments = response.data;
                   });
    }
    },
    
    computed: {
       commentsNot() {
          return this.comments;
       }
    }
 });