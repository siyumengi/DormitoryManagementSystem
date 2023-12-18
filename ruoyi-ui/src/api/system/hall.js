import request from '@/utils/request'

// 查询hall列表
export function listHall(query) {
  return request({
    url: '/system/hall/list',
    method: 'get',
    params: query
  })
}

// 查询hall详细
export function getHall(id) {
  return request({
    url: '/system/hall/' + id,
    method: 'get'
  })
}

// 新增hall
export function addHall(data) {
  return request({
    url: '/system/hall',
    method: 'post',
    data: data
  })
}

// 修改hall
export function updateHall(data) {
  return request({
    url: '/system/hall',
    method: 'put',
    data: data
  })
}

// 删除hall
export function delHall(id) {
  return request({
    url: '/system/hall/' + id,
    method: 'delete'
  })
}
