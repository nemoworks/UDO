import { useState } from 'react'
import { history } from 'umi'
import { Layout, Menu } from 'antd'
import { Icon } from '@/components'
import './index.sass'

const { Sider, Content } = Layout

export default function DefaultLayout({ children }: any) {
  const [collapse, setCollapse] = useState(true)

  const [schemas, setSchemas] = useState([])

  return (
    <Layout>
      <Sider
        collapsible
        trigger={null}
        collapsed={collapse}
        width="140px"
        collapsedWidth="40px"
      >
        <div className="logo" onClick={() => setCollapse(!collapse)}>
          <Icon type="iconIoT" />
          <span className="text">UDO</span>
        </div>

        <Menu theme="dark" mode="inline">
          <Menu.Item
            icon={<Icon type="iconschema" />}
            onClick={() => history.push('/schema')}
          >
            Schema
          </Menu.Item>
        </Menu>
      </Sider>
      <Content>{children}</Content>
    </Layout>
  )
}
