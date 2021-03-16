import { __fragment__, aggregatedOperation as Q } from '@perish/react-xform'

function Options({ schema, children, index }) {
  const { items } = schema

  return children.length === 0 ? (
    <button
      onClick={() =>
        Q(() => {
          if (schema.items === undefined) schema.items = []
          schema.items.push({})
        })
      }
    >
      {schema.title}初始化子项
    </button>
  ) : (
    children.map((line, index) => {
      const operators = (
        <div className={'Options container ' + index} key="operator">
          <button onClick={_ => Q(() => items.splice(index + 1, 0, {}))}>
            +
          </button>
          <button onClick={_ => Q(() => items.splice(index, 1))}>-</button>
          <button
            onClick={_ =>
              Q(() => {
                ;[items[index], items[index - 1]] = [
                  items[index - 1],
                  items[index],
                ]
              })
            }
            disabled={index === 0}
          >
            ↑
          </button>
          <button
            onClick={_ =>
              Q(() => {
                ;[items[index], items[index + 1]] = [
                  items[index + 1],
                  items[index],
                ]
              })
            }
            disabled={index + 1 === items.length}
          >
            ↓
          </button>
        </div>
      )
      return Array.isArray(line)
        ? line.concat(operators)
        : [line].concat(operators)
    })
  )
}

Options[__fragment__] = true

export default Options
