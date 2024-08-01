<template>
    <el-row>
      <el-col :span="6">
        <div class="grid-content ep-bg-purple">
          <!-- <div id="main-catalog"><CatalogTree @nodeClickEvent="nodeClick"></CatalogTree></div> -->
          <div id="main-catalog"><CatalogTree></CatalogTree></div>
        </div>
      </el-col>
      <el-col :span="18">
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
  
  import CatalogTree from './CatalogTree.vue'
  // import NoteContent from './NoteContent.vue'
  
  // import {ref} from 'vue'
  
  // const noteId = ref('0')
  
  // const nodeClick = (msg: string) => {
  //   noteId.value = msg
  // }
  
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
  
  </style>
  