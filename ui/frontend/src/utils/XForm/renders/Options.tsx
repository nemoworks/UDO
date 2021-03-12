import { Button } from 'antd'
import { __fragment__, aggregatedOperation as Q } from '@perish/react-xform'

function Options({ schema, children }) {
  const { items } = schema

  return children.map((line, index) => {
    const operators = (
      <div className="xform-component__Options__container" key="operator">
        <Button
          className="xform-component__Options__operator"
          onClick={_ => Q(() => items.splice(index + 1, 0, {}))}
        >
          +
        </Button>
        <Button
          className="xform-component__Options__operator"
          onClick={_ => Q(() => items.splice(index, 1))}
        >
          -
        </Button>
        <Button
          className="xform-component__Options__operator"
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
        </Button>
        <Button
          className="xform-component__Options__operator"
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
        </Button>
      </div>
    )
    return Array.isArray(line)
      ? line.concat(operators)
      : [line].concat(operators)
  })
}

Options[__fragment__] = true

export default Options
