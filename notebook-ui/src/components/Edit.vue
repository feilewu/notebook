<template>
    <div style="float: right; margin-top: 8px; margin-bottom: 16px; margin-right: 30px;">
      <el-button type="primary" v-on:click="saveNote">保存</el-button>
    </div>
    <!-- 编辑器的容器，注意id要和 new Vditor 绑定的值一致-->
    <div id="vditor"></div>
  </template>


<script lang="ts" setup>
import Vditor from 'vditor'
import { onMounted } from 'vue'
import {getNoteById, createNote, updateNoteById, Note, Response} from '../http/api'

let vditor: Vditor;

const props = defineProps({
  noteId: String,
})


const note: Note = {
  id: "",
  title: "",
  content: "",
  authorId: -1,
  editorId: -1,
  createTime: -1,
  modifyTime: -1,
  parentId: -1,
}


onMounted(() => {
  const parentIdFromRouter = history.state.parentId
  if(parentIdFromRouter) {
    note.parentId = parseInt(parentIdFromRouter as string)
  }

  vditor = new Vditor('vditor', {
    // 规定编辑器总高度，如果不设置，高度将自适应，页面比较难看
    // 需要根据实际情况自己修改它
    height: 900,
    width: '100%',
    lang: 'zh_CN',
    mode: 'sv',
    toolbarConfig: {
      pin: true,
    },
    cache: {
      enable: false,
    },
    after: () => {
      // 编辑器启动后，直接插入一段`Hello`，正式使用视情况修改
      
      if (props.noteId) {
        getNoteById(props.noteId as string).then((resp) => {
        let response: Response = resp.data
        if (resp.data != null){
          console.log(response.data)
          note.id = response.data.id
          note.title = response.data.title
          note.content=response.data.content
          note.authorId=response.data.authorId
          note.editorId=response.data.editorId
          note.createTime=response.data.createTime
          note.modifyTime=response.data.modifyTime
        }
        vditor.setValue(note.content)
      }) 
      }
   
    },
  })
})

const saveNote = async () => {
    
  note.content = vditor.getValue()

  let response: Response;

  if (note.id) {
    response = (await updateNoteById(note)).data
  } else{
    response = (await createNote(note)).data
    
  }
  console.log(response)
  if (response.code != 200) {
    throw "failed to create or update note"
  }
  if (note.id) {
    window.location.href='#home/page/' + note.id
  } else {
    window.location.href='#home/page/' + response.data
  }

  

}



</script>