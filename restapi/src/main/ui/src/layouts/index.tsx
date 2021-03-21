import { useState } from 'react'
import { Layout, Menu } from 'antd'
import { history } from 'umi'
import { Icon } from '@/components'
import './index.sass'

const { Sider, Content } = Layout

export default function DefaultLayout({ children }: any) {
  const [collapse, setCollapse] = useState(true)

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
          <Menu.Item
            icon={<Icon type="icon-document" />}
            onClick={() => history.push('/document')}
          >
            Document
          </Menu.Item>
        </Menu>
      </Sider>
      <Content>{children}</Content>
    </Layout>
  )
}
