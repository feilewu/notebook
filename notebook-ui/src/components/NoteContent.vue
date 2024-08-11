<template>
<div class="note-title">{{noteData.title}}</div>

<div>

  <el-breadcrumb separator="/">
    <el-breadcrumb-item :to="{ path: '/' }">homepage</el-breadcrumb-item>
    <el-breadcrumb-item>
      <a href="/">promotion management</a>
    </el-breadcrumb-item>
    <el-breadcrumb-item>promotion list</el-breadcrumb-item>
    <el-breadcrumb-item>promotion detail</el-breadcrumb-item>
  </el-breadcrumb>
  
  <div class="button-group">
  <el-button
      key="info"
      type="info"
      text
      size="large"
      v-on:click="toNewNote"
    >
    新建
  </el-button>
  
  <el-button
      key="info"
      type="info"
      text
      size="large"
      v-on:click="toEditor"
    >
    编辑
  </el-button>
</div>

</div>




<div id="main_content" v-html="noteData.content"></div>

</template>

<script setup lang="ts">

import {onMounted, onUpdated, ref, Ref} from 'vue';
import {getNoteById, Response} from '../http/api'
import Vditor from 'vditor'
import 'vditor/dist/index.css'
import router from '../router/router';
import 'highlight.js/styles/atom-one-light.css' // 样式 
import 'highlight.js/lib/common' // 依赖包
import hljs from "highlight.js";

const props = defineProps({
  noteId: String,
})

interface Note {
    id: string
    title: string
    content: string
}

const noteData: Ref<Note> = ref({
  id: '-1',
  title: '',
  content: '',
})

const options: IPreviewOptions = {
    mode: "light", 
    cdn: ' https://unpkg.com/vditor@3.10.4',
  
  }

const toHtml = (markdown: string): void => {
  
  Vditor.md2html(markdown, options).then(html => {
    noteData.value.content = html
  })
}

const render = () => {

  getNoteById(props.noteId as string).then(
      (resp) => {
        let response: Response = resp.data

        if (response.data === null) {
          alert("can not get note msg with noteId: " + props.noteId)
          throw "can not get note msg with noteId: " + props.noteId
        }
        noteData.value.id = response.data.id
        noteData.value.title = response.data.title
        console.log("准备渲染文章")
        toHtml(response.data.content)
        
      }
  )
}

onMounted(() => {
  render()
  document.querySelectorAll('pre code').forEach((el) => {
        hljs.highlightElement(el as HTMLElement);
  });
})

onUpdated(() => {
  render()
  document.querySelectorAll('pre code').forEach((el) => {
        hljs.highlightElement(el as HTMLElement);
  });
})


const toEditor = () => {

  router.push("/edit/" + noteData.value.id)

  //window.location.href='#/edit/' + noteData.value.id
}

const toNewNote = () => {
  router.push({
    name:"create",
    state:{
      parentId: props.noteId
    }
    })


  //window.location.href='#/edit/' + noteData.value.id
}


// watch(
//     () => props.noteId,
//     (noteId) => {
//         console.log("props of noteContent has changed!")
//         getNoteById(noteId as string).then(
//             (note) => {
//               if (note.data === null) {
//                 alert("can not get note msg with noteId: " + noteId)
//                 throw "can not get note msg with noteId: " + noteId
//               }
//               noteData.value.id = note.data.id
//               noteData.value.title = note.data.title
//               toHtml(note.data.content)
//             })
//     }
// );

</script>

<style scoped>
.note-title {
  color: #000000;
  font-size: 36px;
  font-family: "Arial, Helvetica, sans-serif";
  margin-top: 5px;
  margin-bottom: 5px;
  font-weight: bolder;
}

.button-group {
  margin-top: 10px;
}

a:link {
  color: #000;
  text-decoration: none;
}
</style>

<style>
  table {
    border-collapse: collapse;
    /* margin: 0 auto; */
    text-align: center;
    width: 70%;
    table-layout: fixed;
  }
  table td, table th {
    border: 1px solid #cad9ea;
    color: #666;
    height: 30px;
  }
  table thead th {
    background-color: #CCE8EB;
    width: 100px;
  }
  table tr:nth-child(odd) {
    background: #fff;
  }
  table tr:nth-child(even) {
    background: #F5FAFA;
  }

</style>