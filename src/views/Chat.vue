<template>
  <div class="chat">
    <h1>mini_chat</h1>
    <div class="container">
      <div class="row">
        <div class="col-sm-4">
          <Conversas />
        </div>
        <div class="col-sm-8">
          <Mensagens />
          <InputMessage />
        </div>
      </div>
    </div>
  </div>
</template>


<script>
// @ is an alias to /src
import Conversas from '@/components/Conversas.vue'
import InputMessage from '@/components/InputMessage.vue'
import Mensagens from '@/components/Mensagens.vue'

export default {
  name: 'Chat',
  components: {
    Conversas,
    InputMessage,
    Mensagens
  }
}
</script>
