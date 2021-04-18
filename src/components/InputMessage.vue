<template>
  <div class="input_message">
    <b-input-group prepend="Mensagem" class="mt-3">
    <b-form-input></b-form-input>
    <b-input-group-append>
      <b-button variant="outline-success">
        <BIconCursor></BIconCursor>
      </b-button>
    </b-input-group-append>
  </b-input-group>
  </div>
</template>


<script>
import {BIconCursor} from 'bootstrap-vue'

export default {
  name: 'InputMessage',
  components: {
    BIconCursor
  },
  props: {
    msg: String
  }
}
</script>