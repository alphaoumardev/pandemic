<template>
  <div style="background-color: #494949; width: 100%; height: 100%">
    <div class="head404"></div>
    <div class="txtbg404">
      <div class="txtbox">
        <p>Sorry ，This page does not exist, or not found</p>
        <p class="paddingbox">Please check the next one</p>
        <p> >> <a @click="$router.go(-1)">Back</a></p>
        <p> >> <a @click="$router.push('/main')">Home</a></p>
      </div>
    </div>
  </div>
</template>

<style lang="less" scoped>
.head404 {
  width: 580px;
  height: 234px;
  margin: 0 auto 0 auto;
  background: url('../assets/img/head404.png') no-repeat;
}

.txtbg404 {
  width: 499px;
  height: 169px;
  margin: 10px auto 0 auto;
  background: url('../assets/img/txt404.png') no-repeat;
}

.txtbg404 .txtbox {
  width: 390px;
  position: relative;
  top: 30px;
  left: 60px;
  color: #eee;
  font-size: 13px;
}

.txtbg404 .txtbox p {
  margin: 5px 0;
  line-height: 18px;
}

.txtbg404 .txtbox .paddingbox {
  padding-top: 15px;
}

a {
  cursor:pointer
}

.txtbg404 .txtbox p a {
  color: #eee;
  text-decoration: none;
}

.txtbg404 .txtbox p a:hover {
  color: #FC9D1D;
  text-decoration: underline;
}
</style>
