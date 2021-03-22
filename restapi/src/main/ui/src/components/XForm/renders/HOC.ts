import React from 'react'
import { __depth__ } from '../utils'

export default function HOC(f) {
  return function ({ schema, index, children }) {
    return React.cloneElement(f({ schema, index, children }), {
      'data-index': index,
      'data-depth': schema[__depth__],
    })
  }
}
