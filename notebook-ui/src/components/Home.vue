<template>
    <el-row>
      <el-col :span="info.catalogColSize" :class="{ hidden: !info.showCatalog }">
        <div class="grid-content ep-bg-purple">
          <!-- <div id="main-catalog"><CatalogTree @nodeClickEvent="nodeClick"></CatalogTree></div> -->
          <div id="main-catalog">
              <CatalogTree :spaceId = props.spaceId></CatalogTree>       
          </div>
        </div>
      </el-col>
      <el-col :span="info.contentColSize">
        <div class="grid-content ep-bg-purple-light">
          <div id="main-content">
            <router-view />
            <!-- <NoteContent :noteId="noteId"></NoteContent> -->
          </div>
        </div>
      </el-col>
    </el-row>
  </template>
  
  <script setup lang="ts">
  
  import { watch, ref } from 'vue';
  import CatalogTree from './CatalogTree.vue'
  import router from '../router/router';

  const props = defineProps({
    spaceId: String,
    showCatalog: {
      type: Boolean,
      default: true
    }
  })

  const info = ref({
    showCatalog: props.showCatalog,
    catalogColSize: 6,
    contentColSize: 18,
  })
  

  watch(
      () => router.currentRoute.value.path,
      (n, ) => {
        if (n.endsWith('edit')) {
          info.value.showCatalog = false
          info.value.catalogColSize = 0
          info.value.contentColSize = 24
        } else {
          info.value.showCatalog = true
          info.value.catalogColSize = 6
          info.value.contentColSize = 18
        }
      }
    )

  </script>
  
  
  <style scoped>
  .main-catalog {
    max-width: 400px;
    height: 900px;
    overflow-x: scroll;
    overflow-y: scroll;
    background-color: rgb(227,225,225)
  }
  
  .main-content {
    min-height: 300px;
  }

  .hidden {
    display: none;
  }

  </style>
  