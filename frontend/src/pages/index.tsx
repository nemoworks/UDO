import XForm, { __render__, wrapAsDependency as $ } from '@xform/react'
import {
  Info,
  Input,
  XObject,
  XArray,
} from '@xform/react/dist/transformer/render'
import './index.sass'

export default () => {
  return (
    <div className="page home container">
      {/* <input type="text" className="search" /> */}
      {/* <div className="slogan">HOME</div> */}
      <XForm
        schema={{
          [__render__]: [XObject],
          properties: {
            sum: {
              [__render__]: [Info],
              data: $((node: any) => {
                const { inputs } = node.$
                let sum = 0
                inputs.items.forEach((item: any) => (sum += Number(item.data)))
                return sum
              }),
            },
            inputs: {
              [__render__]: [XArray],
              template: {
                [__render__]: [Input],
              },
              items: Array(30)
                .fill(null)
                .map(() => ({
                  data: '0',
                })),
            },
          },
        }}
      />
    </div>
  )
}
