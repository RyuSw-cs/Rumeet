package com.d204.rumeet.ui.chatting.chatting_list

import android.os.Build.VERSION_CODES.P
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.d204.rumeet.R
import com.d204.rumeet.databinding.FragmentChattingListBinding
import com.d204.rumeet.ui.base.BaseFragment
import com.d204.rumeet.ui.chatting.chatting_list.adapter.ChattingListAdapter
import com.d204.rumeet.util.AMQPManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class ChattingListFragment : BaseFragment<FragmentChattingListBinding, ChattingListViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_chatting_list

    override val viewModel: ChattingListViewModel by viewModels()
    private lateinit var chattingListAdapter: ChattingListAdapter

    override fun initStartView() {
        with(binding) {
            vm = viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        exception = viewModel.errorEvent
        viewModel.requestChattingRoom()
    }

    override fun initDataBinding() {
        lifecycleScope.launchWhenResumed {
            viewModel.chattingListSideEffect.collectLatest {
                when (it) {
                    is ChattingListSideEffect.SuccessGetChattingList -> {
                        AMQPManager
                        setNoResult(it.isEmpty)
                    }
                    is ChattingListSideEffect.NavigateChattingRoom -> {
                        navigate(
                            ChattingListFragmentDirections.actionChattingListFragmentToChattingFragment(
                                profile = it.profile,
                                chattingRoomId = it.chattingRoomId
                            )
                        )
                    }
                    is ChattingListSideEffect.SuccessNewChattingList -> {
                        chattingListAdapter.submitList(null)
                        chattingListAdapter.submitList(it.chattingRoomInfo.toList())
                        toastMessage("확인")
                    }
                }
            }
        }
    }

    private fun setNoResult(isEmpty: Boolean) {
        if (isEmpty) {
            binding.contentNoResultChattingList.root.visibility = View.VISIBLE
            binding.contentNoResultChattingList.tvContentNoResultMessage.text = "채팅 내역이 없습니다"
        }
    }

    override fun initAfterBinding() {
        chattingListAdapter = ChattingListAdapter(viewModel)
        binding.rvChattingRoom.adapter = chattingListAdapter

        binding.contentNoResultChattingList.tvContentNoResultMessage.text = "채팅내역이 존재하지 않습니다"
    }
}