import XForm, { wrapAsDependency as $ } from '@xform/react'
import transformer from '@xform/react/dist/transformer/sync'
import './index.sass'

export default () => {
  return (
    <div className="page home container">
      <XForm
        schema={{
          type: 'object',
          properties: {
            sum: {
              type: 'info',
              data: $(node =>
                node.$.values.items.reduce(
                  (sum, item) => sum + Number(item.data),
                  0,
                ),
              ),
            },
            values: {
              type: 'array',
              template: {
                type: 'number',
              },
              items: Array(30)
                .fill(null)
                .map(() => ({
                  data: '0',
                })),
            },
          },
        }}
        transformer={transformer}
      />
    </div>
  )
}
