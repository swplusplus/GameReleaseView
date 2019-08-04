<template>
  <div class="tile">
    <a :href="release.shop_url" target="_blank">
      <figure class="image">
        <img :src="release.primary_image" />
      </figure>
    </a>
  </div>
</template>

<script>
export default {
  props: ["release"]
};
</script>
