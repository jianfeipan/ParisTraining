const app = getApp()

var getSmallClassList = function(that){
  wx.request({
    url: 'http://localhost/smallClass.php',
    method: 'GET',
    header: { 'Content-Type': 'application/json' },
    data: {},
    success: function (res) {
      console.log(res.data);
      that.setData({ smallClassUsers: res.data })

    },
    fail: function (res) {
      console.log("get smallclass list failed");
    },
    complete: function (res) {
      // complete
    }
  })
}

Page({
  data: {
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    smallClassUsers:[],
    dialogShow: false,
    buttons: [{ text: '取消' }, { text: '确定' }],
  }, 

  //事件处理函数
  bindViewTap: function() {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  onLoad: function () {
    if (app.globalData.userInfo) {
      this.setData({
        userInfo: app.globalData.userInfo,
        hasUserInfo: true
      })
    } else if (this.data.canIUse){
      // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
      // 所以此处加入 callback 以防止这种情况
      app.userInfoReadyCallback = res => {
        this.setData({
          userInfo: res.userInfo,
          hasUserInfo: true
        })
      }
    } else {
      // 在没有 open-type=getUserInfo 版本的兼容处理
      wx.getUserInfo({
        success: res => {
          app.globalData.userInfo = res.userInfo
          this.setData({
            userInfo: res.userInfo,
            hasUserInfo: true
          })
        }
      })
    }

    var that = this;
    getSmallClassList(that);
  },
  getUserInfo: function(e) {
    console.log(e)
    app.globalData.userInfo = e.detail.userInfo
    this.setData({
      userInfo: e.detail.userInfo,
      hasUserInfo: true
    })
  },
  sendClassRequest:function(e){
    var currentUser = this.data.userInfo.nickName
    var list = this.data.smallClassUsers

    if (currentUser!=""){
      if (list.some(e=> e.user_name === currentUser)){
        console.log("already in smallClass")
      }
      else{
        console.log(e)
        console.log(this.data.userInfo)
        console.log(this.data.userInfo.nickName)
        this.data.smallClassUsers.push({ user_name: this.data.userInfo.nickName })
        console.log(this.data.smallClassUsers)
        this.setData({
          smallClassUsers: this.data.smallClassUsers
        })
      }
    }
  }
})
