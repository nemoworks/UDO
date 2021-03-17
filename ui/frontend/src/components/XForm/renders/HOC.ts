import React from 'react'
import { __depth__ } from '../utils'

export default function HOC(f) {
  return function ({ schema, index, children }) {
    const { [__depth__]: depth } = schema

    return React.cloneElement(f({ schema, index, children }), {
      'data-index': index,
      'data-depth': depth,
    })
  }
}
