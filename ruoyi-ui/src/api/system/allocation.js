import request from '@/utils/request'

// 查询allocation列表
export function listAllocation(query) {
  return request({
    url: '/system/allocation/list',
    method: 'get',
    params: query
  })
}

// 查询allocation详细
export function getAllocation(id) {
  return request({
    url: '/system/allocation/' + id,
    method: 'get'
  })
}

// 新增allocation
export function addAllocation(data) {
  return request({
    url: '/system/allocation',
    method: 'post',
    data: data
  })
}

// 修改allocation
export function updateAllocation(data) {
  return request({
    url: '/system/allocation',
    method: 'put',
    data: data
  })
}

// 删除allocation
export function delAllocation(id) {
  return request({
    url: '/system/allocation/' + id,
    method: 'delete'
  })
}
