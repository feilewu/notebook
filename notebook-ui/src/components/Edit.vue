<template>
    <div style="float: right; margin-top: 8px; margin-bottom: 8px; margin-right: 30px;">
      <el-button type="primary" v-on:click="saveNote">保存</el-button>
    </div>
    <div>
      <el-input v-model="note.title" class="input" placeholder="Note title" />
    </div>
    <!-- 编辑器的容器，注意id要和 new Vditor 绑定的值一致-->
    <div id="vditor"></div>
  </template>


<script lang="ts" setup>
import Vditor from 'vditor'
import { onMounted, ref, Ref } from 'vue'
import {getNoteById, createNote, updateNoteById, Note, Response} from '../http/api'
import router from '../router/router';

let vditor: Vditor;

const props = defineProps({
  noteId: String,
})



const note: Ref<Note> = ref({
  id: "",
  title: "",
  content: "",
  authorId: -1,
  editorId: -1,
  createTime: -1,
  modifyTime: -1,
  parentId: -1,
})


onMounted(() => {
  const parentIdFromRouter = history.state.parentId
  if(parentIdFromRouter) {
    note.value.parentId = parseInt(parentIdFromRouter as string)
  }

  vditor = new Vditor('vditor', {
    // 规定编辑器总高度，如果不设置，高度将自适应，页面比较难看
    // 需要根据实际情况自己修改它
    height: 890,
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
          note.value.id = response.data.id
          note.value.title = response.data.title.trim()
          note.value.content=response.data.content
          note.value.authorId=response.data.authorId
          note.value.editorId=response.data.editorId
          note.value.createTime=response.data.createTime
          note.value.modifyTime=response.data.modifyTime
        }
        vditor.setValue(note.value.content)
      }) 
      }
   
    },
  })
})

const saveNote = async () => {
    
  note.value.content = vditor.getValue()

  let response: Response;

  if (note.value.id) {
    response = (await updateNoteById(note.value)).data
  } else{
    response = (await createNote(note.value)).data
    
  }

  if (response.code != 200) {
    throw "failed to create or update note"
  }
  if (note.value.id) {

    //router.back()

    window.location.href='#home/page/' + note.value.id
  } else {
    
    //router.back()
    window.location.href='#home/page/' + response.data
  }

}


</script>

<style scoped>
.input {
  width:100%;
  height: 40px;
  font-size: 17px;
}





</style>