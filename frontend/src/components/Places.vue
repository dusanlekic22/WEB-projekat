<template>
  <div>
    <input type="text" />
    <button class="btn1">Pretraži</button>
    <ul>
      <li v-for="(result, i) in searchResults" :key="i">
        {{ result }} // list of all places
      </li>
    </ul>
  </div>
</template>

<style scoped>
.btn1 {
  height: 51px;
  width: 17%;
  border: none;
  outline: none;
  background-color: rgb(25, 153, 238);
  color: white;
  font-weight: 700;
  border-radius: 10px;
  margin-left: 15px;
}
input {
  height: 51px;
  width: 300px;
  border-radius: 10px;
  border: none;
}
</style>
