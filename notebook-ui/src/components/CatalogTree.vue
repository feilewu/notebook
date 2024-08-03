<template>
    <el-tree
      ref="treeRef"
      style="max-width: 600px"
      :props="props"
      :load="loadNode"
      node-key="id"
      lazy
      @node-click="handleNodeClick"
      
    ></el-tree>
  </template>
  
  <script lang="ts" setup>
  import type Node from 'element-plus/es/components/tree/src/model/node'
  import {getCatalogNode} from '../http/api'
  import { AxiosResponse } from 'axios';

  interface Tree {
    id: string
    name: string
    leaf?: boolean
  }
  
  const props = {
    label: 'name',
    children: 'zones',
    isLeaf: 'leaf',
  }

  const loadNode = (node: Node, resolve: (data: Tree[]) => void) => {
    if (node.level === 0) {
      return resolve([{ id: '0', name: 'Notebook' }])
    }
    //if (node.level > 1) return resolve([])
  
    setTimeout(async () => {
      let data: AxiosResponse<Tree[]> = await getCatalogNode(node.key + '')
      console.log(data.data)
      resolve(data.data)
    }, 500)
  }

  const emit = defineEmits<{
  (e: 'nodeClickEvent', id: string): void
  }>()

  const handleNodeClick = (data: any) => {
    //if (data.id != 0) {
      //emit('nodeClickEvent', data.id)
      console.log(data.id)
      window.location.href='#home/page/' + data.id
    //} 
  }

  </script>