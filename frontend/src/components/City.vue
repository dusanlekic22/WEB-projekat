<template>
  <div class="col-lg-3 col-md-3 mt-md-3 d-flex">
    <div class="item-card">
      <div class="body">
        <div class="card-title red">
          <h5>{{ name }}</h5>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ["id", "name"],
};
</script>

<style scoped>
/* Custom cards */
.cards-section h3,
h5 {
  font-weight: 700 !important;
}

.btn-green {
  background-color: #17c3ba;
  color: #fff;
}

.btn:hover {
  color: #fff;
  opacity: 0.8 !important;
}

.btn-pad {
  padding-bottom: 50px;
}

.item-card {
  border: 1px solid #ddd;
  border-radius: 5px;
  width: 200px;
  height: 100%;
}

.card-title {
  padding: 30px;
  border-radius: 5px 5px 0 0;
  color: #fff;
  text-align: center;
}

.body-text {
  height: 100px;
  padding: 0 20px 10px;
}

/* colors  */

.red {
  background-color: #eb5168;
}
.l-blue {
  background-color: #4ec1fc;
}

.green {
  background-color: #17c3ba;
}

.blue {
  background-color: #1d386c;
}
</style>
