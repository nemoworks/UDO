import {
  aggregatedOperation as Q,
  __fragment__,
  __render__,
} from '@perish/react-xform'
import { Icon } from '@/components'
import { __depth__ } from '../utils'

function Options({ schema, children, index }) {
  const { items, [__depth__]: depth } = schema

  if (children.length === 0)
    return (
      <button
        className="Options create"
        onClick={() =>
          Q(() => {
            if (schema.items === undefined) schema.items = []
            schema.items.push({})
          })
        }
      >
        {schema.title}初始化子项
      </button>
    )

  return children.map((line, index) => {
    const operators = (
      <div
        className="Options operators"
        data-index={index}
        data-depth={depth}
        key="operator"
      >
        <Icon
          type="icontianjia"
          onClick={_ => Q(() => items.splice(index + 1, 0, {}))}
        />
        <Icon
          type="iconshanchu"
          onClick={_ => Q(() => items.splice(index, 1))}
        />
      </div>
    )
    return Array.isArray(line)
      ? line.concat(operators)
      : [line].concat(operators)
  })
}

Options[__fragment__] = true

export default Options
