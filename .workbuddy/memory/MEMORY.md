# TerrariaMine-MultiLoader 项目记录

## 项目信息
- **仓库名**: TerrariaMine-MultiLoader
- **GitHub**: https://github.com/Ricky-EGAWA/TerrariaMine-MultiLoader
- **开发语言**: Java (Minecraft Mod - Fabric/Forge)
- **项目内容**: 将泰拉瑞亚中的怪物、武器、防具等物品添加到我的世界

## 技术栈
- Minecraft Mod 开发
- 支持 Fabric 和 Forge 两个版本
- 使用 Gradle 构建系统

## 已完成的工作

### 2026-03-26: 数值调整
根据 Terraria Wiki 1.4.4 官方数据,调整了武器伤害和防具防御力:

**武器伤害调整:**
| 武器 | 旧伤害 | 新伤害 |
|------|--------|--------|
| Cobalt Sword | 5 | 7 |
| Orichalcum Sword | 5 | 9 |
| Adamantite Sword | 5 | 13 |
| Hellstone Sword | 5 | 6 |

**工具基础伤害调整:**
| 工具材料 | 旧伤害 | 新伤害 |
|----------|--------|--------|
| Cobalt | 6f | 5f |
| Orichalcum | 7.5f | 6f |
| Adamantite | 9f | 8f |
| Hellstone | 5f | 4f |

**防具防御力调整(符合 Terraria 防御力比例):**
| 防具套装 | 旧值 | 新值(总计) |
|----------|------|-----------|
| Cobalt | [4,8,6,4] | [6,8,6,5] = 25 |
| Orichalcum | [5,12,9,4] | [7,12,9,2] = 30 |
| Adamantite | [6,16,12,6] | [8,16,12,0] = 36 |
| Hellstone | [3,8,6,3] | [3,8,6,3] = 20 |

**额外修复:**
- Hellstone 防具的修复材料从 Adamantite Ingot 改为 Hellstone Ingot

**Git 提交:**
- 分支: terraria-branch
- 提交信息: "调整 Terraria 武器和防具数值以匹配官方数据"
- 推送成功到: https://github.com/Ricky-EGAWA/TerrariaMine-MultiLoader

## 开发工具

### terramod-helper Skill (2026-03-27)
- **位置**: `~/.workbuddy/skills/terramod-helper/`
- **用途**: 加速 TerrariaMine 模组开发
- **包含**: 项目架构参考、Terraria数值表、Item/Block/Entity代码模板
- **触发**: 用户提到添加物品/方块/怪物/武器/防具等 Terraria 相关内容时自动加载

## GitHub 配置
- **Personal Access Token**: Fine-grained token ("work buddy")
- **配置的仓库**: Ricky-EGAWA/TerrariaMine-MultiLoader
- **权限**: Contents (Read and write), Metadata (Read)
- **当前工作分支**: terraria-branch
