import request from '@/utils/request'

// 查询balance列表
export function listBalance(query) {
  return request({
    url: '/system/balance/list',
    method: 'get',
    params: query
  })
}

// 查询balance详细
export function getBalance(id) {
  return request({
    url: '/system/balance/' + id,
    method: 'get'
  })
}

// 新增balance
export function addBalance(data) {
  return request({
    url: '/system/balance',
    method: 'post',
    data: data
  })
}

// 修改balance
export function updateBalance(data) {
  return request({
    url: '/system/balance',
    method: 'put',
    data: data
  })
}

// 删除balance
export function delBalance(id) {
  return request({
    url: '/system/balance/' + id,
    method: 'delete'
  })
}
