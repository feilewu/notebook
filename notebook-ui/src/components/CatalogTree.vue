  <template>
    <el-tree
      ref="treeRef"
      style="max-width: 600px"
      :props="props"
      :load="loadNode"
      node-key="id"
      lazy
      :expand-on-click-node="false"
      @node-click="handleNodeClick"
    ></el-tree>
  </template>
  
  <script lang="ts" setup>
  import type Node from 'element-plus/es/components/tree/src/model/node'
  import {getCatalogNode, Response} from '../http/api'
  import { AxiosResponse } from 'axios'
  import { Tree } from '../type'
  import router from '../router/router';
  //import { catalogTreeStore } from '../store'

  const props = {
    label: 'name',
    children: 'zones',
    isLeaf: 'leaf',
  }

  const info = defineProps({
    spaceId: {
      type: String,
      defalt: "-1"
    }
  })

  //const treeStore = catalogTreeStore()
  
  // const data: Tree[] = catalogTreeStore().data

  const loadNode = (node: Node, resolve: (data: Tree[]) => void) => {
    if (node.level === 0) {
      return resolve([{ id: '0', name: 'Notebook' }])
    }
    //if (node.level > 1) return resolve([])
  
    setTimeout(async () => {
      let resp: AxiosResponse<Response> = await getCatalogNode(node.key + '')
      let response: Response = resp.data
      let tree: Tree[] = response.data
      resolve(tree)
    }, 500)
  }

  const emit = defineEmits<{
  (e: 'nodeClickEvent', id: string): void
  }>()

  const handleNodeClick = (data: any) => {

    if (data.id == 0) {

      router.push({
        name: "space"
      })
      return
    }

    router.push("/space/" + info.spaceId + "/page/" + data.id)
  }

  </script>