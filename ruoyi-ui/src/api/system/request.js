import request from '@/utils/request'

// 查询request列表
export function listRequest(query) {
  return request({
    url: '/system/request/list',
    method: 'get',
    params: query
  })
}

// 查询request详细
export function getRequest(id) {
  return request({
    url: '/system/request/' + id,
    method: 'get'
  })
}

// 新增request
export function addRequest(data) {
  return request({
    url: '/system/request',
    method: 'post',
    data: data
  })
}

// 修改request
export function updateRequest(data) {
  return request({
    url: '/system/request',
    method: 'put',
    data: data
  })
}

// 删除request
export function delRequest(id) {
  return request({
    url: '/system/request/' + id,
    method: 'delete'
  })
}
