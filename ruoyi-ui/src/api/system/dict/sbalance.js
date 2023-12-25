import request from '@/utils/request'

// 查询balance列表
export function listBalance(query) {
  return request({
    url: '/system/sbalance/list',
    method: 'get',
    params: query
  })
}

// 查询balance详细
export function getBalance(id) {
  return request({
    url: '/system/sbalance/' + id,
    method: 'get'
  })
}

// 新增balance
export function addBalance(data) {
  return request({
    url: '/system/sbalance',
    method: 'post',
    data: data
  })
}

// 修改balance
export function updateBalance(data) {
  return request({
    url: '/system/sbalance',
    method: 'put',
    data: data
  })
}

// 删除balance
export function delBalance(id) {
  return request({
    url: '/system/sbalance/' + id,
    method: 'delete'
  })
}
